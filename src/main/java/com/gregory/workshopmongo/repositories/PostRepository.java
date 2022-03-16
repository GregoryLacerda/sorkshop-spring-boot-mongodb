package com.gregory.workshopmongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gregory.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	//consulta simple com o @Query usando o formato do Json padrão do mongoDB para realizar a consulta
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	//Metodo query que busca no campo title um texto recebido //IgnoreCase para ignorar o maiusculo e minusculo 
	List<Post> findByTitleContainingIgnoreCase(String text);

}