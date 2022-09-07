package com.fiap.transactionsAPI.controller;

import com.fiap.transactionsAPI.dto.ReportDTO;
import com.fiap.transactionsAPI.dto.StudentDTO;
import com.fiap.transactionsAPI.service.CardService;
import com.fiap.transactionsAPI.service.ReportService;
import com.fiap.transactionsAPI.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public class StundentController {

    private final StudentService studentService;
    private final CardService cardService;
    private final ReportService reportService;

    public StundentController(StudentService studentService,
                              CardService cardService,
                              ReportService reportService) {

        this.studentService = studentService;
        this.cardService = cardService;
        this.reportService = reportService;
    }

    @GetMapping(value = "{id}")
    public StudentDTO findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO insertStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.insert(studentDTO);
    }

    @PutMapping(value = "{id}")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable String id) {
        studentDTO.setRa(Long.valueOf(id));
        return studentService.update(studentDTO);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Object id) {
        Long cardNumber = studentService.delete(id);
        cardService.delete(cardNumber);
    }

    @GetMapping(value = "/ra/{ra}")
    public StudentDTO findByRa(@PathVariable Long ra) {
        return studentService.findByRa(ra);
    }

    @GetMapping
    public Object find(@RequestParam(value = "ra", defaultValue = "") Long ra) {
        return ra == null ? studentService.findAll() : studentService.findByRa(ra);
    }

    @GetMapping(value = "teste")
    public String teste() {
        return "teste";
    }

    @PostMapping(value = "{ra}/card")
    public StudentDTO generateCreditCard(@PathVariable Long ra) {
        return cardService.generateCard(ra);
    }

    @GetMapping(value = "{ra}/report")
    public ReportDTO report(@PathVariable Long ra) {
        return reportService.generateReport(ra);
    }

}
