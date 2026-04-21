package it.aulab.esercizio_spring_data;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import it.aulab.esercizio_spring_data.models.Author;
import it.aulab.esercizio_spring_data.models.Comment;
import it.aulab.esercizio_spring_data.repositories.AuthorRepository;
import it.aulab.esercizio_spring_data.repositories.CommentRepository;

// @SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class EsercizioSpringDataApplicationTests {

	@Autowired
	AuthorRepository authorRepository;

	@BeforeEach
	void load(){
		Author a1 = new Author();
		a1.setName("Valentino");
		a1.setSurname("Rossi");
		a1.setEmail("vale46@boh.it");
		authorRepository.save(a1);
	}

	@Test
	void contextLoads() {
	}

	// Test su Author

	@Test
	void findByName(){
		assertThat(authorRepository.findByName("Giuseppe"))
		.extracting("name")
		.containsOnly("Giuseppe");
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
		.containsOnly("Giuseppe");
	}
	// Il codice dovrebbe essere corretto, ma mi dice che fallisce nel caricare ApplicationContext. Solo con l'ultimo test, gli altri funzionavano.

	// Test su Comment

	@Autowired
	CommentRepository commentRepository;

	@BeforeEach
	void load(){
		Comment c1 = new Comment();
		c1.setEmail("tizio@boh.it");
		commentRepository.save(c1);
	}

	@Test
	void findByEmail(){
		assertThat(commentRepository.findByEmail())
		.extracting("email")
		.containsOnly("tizio@boh.it");
	}
}
