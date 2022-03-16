package com.gregory.workshopmongo.repositories;

import java.util.Date;
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
	
	//Busca posts contendo um dado string em qualquer lugar (no título, corpo ou comentários) e em um dado intervalo de datas
	//and == operador &&//verifica se date>= minDate//verifica se date<= maxDate //compara com os campos title, body e o text do comentario para trazer o resultado que tenha a info em pelo menos 1 desses
	@Query("{ $and: [ { date: { $gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);

	
}