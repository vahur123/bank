package ee.bcs.bank.restbank;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    // TODO: loo teenus createExampleAccount() mis loob uue AccountDtoSolution objekti:
    //  account number = random account number
    //  firstName "John"
    //  lastName "Smith"
    //  balance 0
    //  locked false

    public AccountDto createExampleAccount() {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(createRandomAccountNumber());
        accountDto.setFirstName("Juss");
        accountDto.setLastName("Sepp");
        accountDto.setBalance(0);
        accountDto.setLocked(false);
        return accountDto;
    }

    private String createRandomAccountNumber() {
        //  Creates random account number between EE1000 -  EE9999
        Random random = new Random();
        return "EE" + (random.nextInt(9999) + 1000);
    }

    public boolean accountIdExist(List<AccountDto> accounts, int accountId) {
        for (AccountDto account : accounts) {
            if (account.getId() == accountId) {
                return true;
            }
        }

        return false;
    }

    public AccountDto getAccountById(List<AccountDto> accounts, int accountId) {
        //  käime läbi kõik kontod 'accounts' listis,
        //  ja paneme iga konto muutjasse 'account'
        for (AccountDto account : accounts) {
            // kui leiame konto mille id on võrdne accountId'ga
            if (account.getId() == accountId) {
                // siis tagastame selle konto
                return account;
            }
        }
        return null;
    }

    public boolean accountNumberExist(List<AccountDto> accounts, String receiverAccountNumber) {
        for (AccountDto account : accounts) {
            if (account.getAccountNumber().equals(receiverAccountNumber)) {
                return true;
            }
        }
    return false;
}

    public AccountDto getAccountByNumber(List<AccountDto> accounts, String receiverAccountNumber) {
        for (AccountDto account : accounts) {
            if (account.getAccountNumber().equals(receiverAccountNumber)) {
                return account;
            }
        }
        return null;
    }

    public RequestResult updateOwnerDetails(List<AccountDto> accounts, AccountDto accountDto) {
        RequestResult requestResult = new RequestResult();

        int accountId = accountDto.getId();
        if(!accountIdExist(accounts, accountId)) {
            requestResult.setError("Account ID" + accountId + "does not exist!");
            return requestResult;
        }

        AccountDto account = getAccountById(accounts, accountId);
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());

        requestResult.setAccountId(accountId);
        requestResult.setMessage("Sucsessfully updated account.");
        return requestResult;

    }

    public RequestResult deleteAccount(List<AccountDto> accounts, int accountId) {
        RequestResult requestResult = new RequestResult();

        if(!accountIdExist(accounts, accountId)) {
            requestResult.setError("Account ID" + accountId + "does not exist!");
            return requestResult;
        }

        AccountDto account = getAccountById(accounts, accountId);
        accounts.remove(account);

        requestResult.setMessage(("Account deleted"));
        requestResult.setAccountId(accountId);
        return requestResult;
    }

    public RequestResult lockAccount(List<AccountDto> accounts, int accountId) {
        RequestResult requestResult = new RequestResult();

        if(!accountIdExist(accounts, accountId)) {
            requestResult.setError("Account ID" + accountId + "does exist!");
            return requestResult;
        }
        AccountDto account = getAccountById(accounts, accountId);
        Boolean lockedStatus = account.getLocked();

        account.setLocked(!lockedStatus);

        requestResult.setMessage(("Locked status"+account.getLocked()));


        requestResult.setAccountId(accountId);

        return requestResult;
    }

    }


