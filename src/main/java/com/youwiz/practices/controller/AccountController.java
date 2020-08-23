package com.youwiz.practices.controller;

import com.youwiz.practices.common.PageRequest;
import com.youwiz.practices.domain.Email;
import com.youwiz.practices.dto.AccountDto;
import com.youwiz.practices.dto.AccountSearchType;
import com.youwiz.practices.repository.AccountRepository;
import com.youwiz.practices.service.AccountSearchService;
import com.youwiz.practices.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountSearchService accountSearchService;
    private final AccountRepository accountRepository;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AccountDto.Res signUp(@RequestBody @Valid final AccountDto.SignUPReq dto) {
        return new AccountDto.Res(accountService.create(dto));
    }

    @GetMapping
    public Page<AccountDto.Res> getAccounts(
            @RequestParam(name = "type") final AccountSearchType type,
            @RequestParam(name = "value", required = false) final String value,
            final PageRequest pageRequest
    ) {
        return accountSearchService.search(type, value, pageRequest.of()).map(AccountDto.Res::new);
    }

    // 컨트롤러 코드
//    @GetMapping
//    public Page<AccountDto.Res> getAccounts(final PageRequest pageable) {
//        return accountService.findAll(pageable.of()).map(AccountDto.Res::new);
//    }

//    기본 Pageable을 사용한 코드
//    @GetMapping
//    public Page<AccountDto.Res> getAccounts(final PageRequest pageable) {
//        return accountService.findAll(pageable).map(AccountDto.Res::new);
//    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public AccountDto.Res getUser(@PathVariable final long id) {
        return new AccountDto.Res(accountService.findById(id));
    }

//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseStatus(value = HttpStatus.OK)
//    public AccountDto.Res getUserByEmail(@Valid Email email) {
//        return new AccountDto.Res(accountService.findByEmail(email));
//    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public AccountDto.Res updateMyAccount(@PathVariable final long id, @RequestBody final AccountDto.MyAccountReq dto) {
        return new AccountDto.Res(accountService.updateMyAccount(id, dto));
    }
}
