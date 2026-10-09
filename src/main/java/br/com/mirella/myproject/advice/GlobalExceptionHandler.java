package br.com.mirella.myproject.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice //permite que a classe trate exceções de maneira global nos controllers e que os objetos retornados sejam convertidos em respostas, normalmente JSON
public class GlobalExceptionHandler {
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class) //indicar qual excecao o metodo vai tratar - MethodArgumentNotValidException: quando os dados recebidos não passam pela validação.
    public ResponseEntity<Map<String, Object>> handleInvalidBody(
            org.springframework.web.bind.MethodArgumentNotValidException ex, //info sobre a excecao e erro de validacao
            jakarta.servlet.http.HttpServletRequest request) { //acessar informações da requisição (URL)

        List<Map<String, String>> fields = ex.getBindingResult() //getBindingResult(): obtém o resultado da validação
                .getFieldErrors() //recupera os erros associados
                .stream()
                .map(err -> Map.of(
                        "name", err.getField(),
                        "message", err.getDefaultMessage()))
                .toList();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "Invalid input");
        body.put("fields", fields);
        body.put("path", request.getRequestURI());

        return ResponseEntity.badRequest().body(body); // 400
    }
}
