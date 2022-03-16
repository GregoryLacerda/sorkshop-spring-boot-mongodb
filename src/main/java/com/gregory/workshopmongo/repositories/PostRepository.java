package com.gregory.workshopmongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gregory.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	//Metodo query que busca no campo title um texto recebido //IgnoreCase para ignorar o maiusculo e minusculo 
	List<Post> findByTitleContainingIgnoreCase(String text);

}