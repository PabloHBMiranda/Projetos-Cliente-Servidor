package com.example.restaurante.model;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
    public static String gerarHash(String senha) throws NoSuchAlgorithmException {
        // Cria um objeto MessageDigest com o algoritmo SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Aplica o algoritmo de hash na senha convertida para bytes
        byte[] hash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));

        // Converte o hash para uma representação hexadecimal
        StringBuilder hexHash = new StringBuilder();
        for (byte b : hash) {
            hexHash.append(String.format("%02x", b));
        }

        // Retorna o hash em formato de string
        return hexHash.toString();
    }
}
