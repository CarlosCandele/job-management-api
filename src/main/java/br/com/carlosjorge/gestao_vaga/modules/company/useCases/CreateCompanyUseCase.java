package br.com.carlosjorge.gestao_vaga.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.carlosjorge.gestao_vaga.exceptions.UserFoundException;
import br.com.carlosjorge.gestao_vaga.modules.company.entities.CompanyEntity;
import br.com.carlosjorge.gestao_vaga.modules.company.repositoreis.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        companyRepository.findByUsernameOrEmail(
            companyEntity.getUsername(), 
            companyEntity.getEmail()
        ).ifPresent(user -> {
            throw new UserFoundException();
        });

        String encodedPassword = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(encodedPassword);

        return companyRepository.save(companyEntity);
    }
}
