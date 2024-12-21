package com.example.entities;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "articles")
@Data
public class Article {
    private String Libelle;
    private String Prix;
    private int quantite;
    private int Montant;
    public Article() {
    }
    
}