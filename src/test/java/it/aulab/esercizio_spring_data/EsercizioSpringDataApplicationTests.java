package it.aulab.esercizio_spring_data;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import it.aulab.esercizio_spring_data.models.Author;
import it.aulab.esercizio_spring_data.models.Comment;
import it.aulab.esercizio_spring_data.models.Post;
import it.aulab.esercizio_spring_data.repositories.AuthorRepository;
import it.aulab.esercizio_spring_data.repositories.CommentRepository;
import it.aulab.esercizio_spring_data.repositories.PostRepository;
import jakarta.persistence.EntityManager;

// @SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class EsercizioSpringDataApplicationTests {

	// Creazione di un autore, un post ed un commento

	@Autowired
	AuthorRepository authorRepository;

	private Post savedPost;

	@Autowired
	PostRepository postRepository;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	EntityManager entityManager;


	@BeforeEach
	void load(){
		Author a1 = new Author();
		a1.setName("Valentino");
		a1.setSurname("Rossi");
		a1.setEmail("vale46@boh.it");
		authorRepository.save(a1);

		savedPost = new Post();
    	savedPost.setTitle("Primo");
    	savedPost.setBody("Contenuto del primo post");
    	savedPost.setPublishDate("23-04-26");
		savedPost = postRepository.save(savedPost);

		Comment c1 = new Comment();
		c1.setEmail("tizio@boh.it");
		c1.setBody("Questo è un commento");
		c1.setPost(savedPost);
		commentRepository.save(c1);

		entityManager.flush();
	}

	// Test su Author

	@Test
	void findByName(){
		assertThat(authorRepository.findByName("Valentino"))
		.extracting("name")
		.containsOnly("Valentino");
	}
	
	@Test
	void authorsWithSameName(){
		assertThat(authorRepository.authorsWithSameName())
		.extracting("name")
		.containsOnly("Valentino");
	}

	@Test
	void sameAuthorNameNonNative(){
		assertThat(authorRepository.sameAuthorNameNonNative())
		.extracting("name")
		.containsOnly("Valentino");
	}
	// Il codice dovrebbe essere corretto, ma mi dice che fallisce nel caricare ApplicationContext. Solo con l'ultimo test, gli altri funzionavano.


	// Test sul model Post

	@Test
	void findByTitle(){
    	assertThat(postRepository.findByTitle("Primo"))
        .extracting("title")
        .containsOnly("Primo");
	}

	@Test
	@Transactional
	void postsWithSameTitle() {
    	assertThat(postRepository.postsWithSameTitle())
        .extracting("title")
        .containsOnly("Primo");
	}

	@Test
	void samePostTitleNonNative() {
    	assertThat(postRepository.samePostTitleNonNative())
        .extracting("title")
        .containsOnly("Primo");
}

	
	// Test su Comment


	@Test
	void findByEmail(){
		assertThat(commentRepository.findByEmail("tizio@boh.it"))
		.extracting("email")
		.containsOnly("tizio@boh.it");
	}

	@Test
	void commentWithSameEmail(){
		assertThat(commentRepository.commentWithSameEmail())
		.extracting("email")
		.containsOnly("tizio@boh.it");
	}

	@Test
	void commentWithSameEmailNonNative(){
		assertThat(commentRepository.commentWithSameEmailNonNative())
		.extracting("email")
		.containsOnly("tizio@boh.it");
	}
}
