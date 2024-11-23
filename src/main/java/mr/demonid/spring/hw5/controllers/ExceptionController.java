package mr.demonid.spring.hw5.controllers;

import mr.demonid.spring.hw5.exceptions.ApplicationBaseException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Глобальный обработчик ошибок.
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * Ошибка отсутствия элемента в БД, скорее всего рассинхронизация данных.
     */
    @ExceptionHandler(ApplicationBaseException.class)
    public String handleNotFound(ApplicationBaseException ex, Model model) {
        model.addAttribute("errorMessage", ex.getHead());
        model.addAttribute("errorDetails", ex.getMessage());
        return "error";
    }

}
