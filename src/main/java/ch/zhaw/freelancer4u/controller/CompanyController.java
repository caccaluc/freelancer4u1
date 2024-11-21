package ch.zhaw.freelancer4u.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.zhaw.freelancer4u.model.Company;
import ch.zhaw.freelancer4u.model.CompanyCreateDTO;
import ch.zhaw.freelancer4u.repository.CompanyRepository;

@RestController
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;

    @PostMapping("/company")
    public ResponseEntity<Company> createCompany(
            @RequestBody CompanyCreateDTO fDTO) {
        Company fDAO = new Company(fDTO.getName(), fDTO.getEmail());
        Company f = companyRepository.save(fDAO);
        return new ResponseEntity<>(f, HttpStatus.CREATED);
    }

    // GET Endpoint zum Abrufen einer Liste aller Unternehmen mit Paginierung
    @GetMapping("/company")
    public ResponseEntity<Page<Company>> getCompanies(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "5") int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Company> allCompanies = companyRepository.findAll(pageable);
        return new ResponseEntity<>(allCompanies, HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable String id) {
        Optional<Company> optCompany = companyRepository.findById(id);
        if (optCompany.isPresent()) {
            return new ResponseEntity<>(optCompany.get(), HttpStatus.OK);
        } else { 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


