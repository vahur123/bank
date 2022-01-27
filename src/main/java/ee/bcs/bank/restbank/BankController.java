package ee.bcs.bank.restbank;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/solution")
public class BankController {

    public static Bank bank = new Bank();

    @Resource
    private AccountService accountService;

    @Resource
    private TransactionService transactionService;

    @Resource
    private BankService bankService;

    @GetMapping("/bank")
    public Bank getBank() {
        return bank;
    }


    // TODO: et saada üks accounts JSON'i näidis,
    //  siis loo uus controlleri endpoint                                           /example/account
    //  meetodi nimeks pane                                                         getExampleAccount()
    //  loo accountService alla uus teenus                                          createExampleAccount()

    @GetMapping("/example/account")
    public AccountDto getExampleAccount() {
        return accountService.createExampleAccount();
    }

    // TODO: et saada üks transaction JSON'i näidis,
    //  siis loo uus controlleri endpoint               /example/transaction
    //  meetodi nimeks pane                             getExampleTransaction()
    //  loo transactionService alla uus teenus          createExampleTransaction()

    @GetMapping("/example/transaction")
    public TransactionDto getExampleTransaction() {
        return transactionService.createExampleTransaction();
    }




    // TODO: Et lisada uus account, loo uus controlleri endpoint                    /new/account
    //  võta RequestBodyst sisse accountDto objekt
    //  loo bankService alla uus teenus                                             addAccountToBank()
    //  ja lisa see konto bank accounts listi
    //  teenus võiks tagastada RequestResultSolution objekti koos koos loodava konto id ja transaktsiooni id'ga

    @PostMapping("/new/account")
    public RequestResult addAccountToBank(@RequestBody AccountDto accountDto) {
        return bankService.addAccountToBank(bank, accountDto);
    }

    @PostMapping("/new/transaction")
    public RequestResult addNewTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.addNewTransaction(bank, transactionDto);
    }
    @PostMapping("/receiver/transaction")
    public RequestResult receiverNewTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.receiverNewTransaction(bank, transactionDto);
    }



    //  loo transactionService alla uus teenus                                      createTransactionForNewAccount()
    //  loo bankService alla uus teenus                                             addTransaction()


}
