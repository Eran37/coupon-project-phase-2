package com.example.couponprojectphase2.security;

import com.example.couponprojectphase2.beans.Admin;
import com.example.couponprojectphase2.beans.Company;
import com.example.couponprojectphase2.beans.Customer;
import com.example.couponprojectphase2.exceptions.SecurityException;
import com.example.couponprojectphase2.exceptions.SecurityMsg;
import com.example.couponprojectphase2.repositories.CompanyRepository;
import com.example.couponprojectphase2.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service // (singletons) all of them managed on RAM memory
@RequiredArgsConstructor // אלעד של מחר - collection injection (config.MapConfig -> initialization bean)
public class TokenManager {

    //    @Autowired אלעד של אתמול
    //      אלעד של היום יעשה עם קולקש'ן אינג'קשן
    private final Map<UUID, Information> map;

    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final Admin admin;


    public UUID add(String email, ClientType clientType) {
        UUID token;
        switch (clientType) {
            case COMPANY:
                Company companyFromDb = companyRepository.findTop1ByEmail(email);
                removePreviousInstances(companyFromDb.getId());
                Information companyInfo = Information.builder()
                        .id(companyFromDb.getId())
                        .email(email)
                        .type(clientType)
                        .time(LocalDateTime.now())
                        .build();
                token = UUID.randomUUID();
                map.put(token, companyInfo);
                return token;
            case CUSTOMER:
                Customer customerFromDb = customerRepository.findTop1ByEmail(email);
                removePreviousInstances(customerFromDb.getId());
                Information customerInfo = Information.builder()
                        .id(customerFromDb.getId()) //
                        .email(email)
                        .type(clientType)
                        .time(LocalDateTime.now())
                        .build();
                token = UUID.randomUUID();
                map.put(token, customerInfo);
                return token;
            case ADMINISTRATOR:
                removePreviousInstances(admin.getId());
                Information adminInfo = Information.builder()
                        .id(37)
                        .email(admin.getEmail())
                        .type(ClientType.ADMINISTRATOR)
                        .time(LocalDateTime.now())
                        .build();
                token = UUID.randomUUID();
                map.put(token, adminInfo);
                return token;
        }
        return null;
    }

    public int getUserId(UUID token) throws SecurityException {
        Information information = map.get(token);
        if (information == null)
            throw new SecurityException(SecurityMsg.INVALID_TOKEN);
        return information.getId();
    }

    public ClientType getClientType(UUID token) throws SecurityException {
        Information information = map.get(token);
        if (information == null)
            throw new SecurityException(SecurityMsg.INVALID_TOKEN);
        return information.getType();
    }

    @Scheduled(fixedRate = 1000 * 60 * 30)
    public void deleteExpiredTokensOver30Minutes() {
        map.entrySet().removeIf(ins -> ins.getValue().getTime().isAfter(LocalDateTime.now().minusMinutes(30)));
    }

    public void removePreviousInstances(int userId) {
        map.entrySet().removeIf(inst -> inst.getValue().getId() == userId);
    }


}
