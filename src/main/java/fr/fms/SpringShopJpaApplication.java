package fr.fms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import fr.fms.dao.ArticleRepository;
//import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
//import fr.fms.entities.Category;


@SpringBootApplication
public class SpringShopJpaApplication  implements CommandLineRunner{
	
	//@Autowired
	//private CategoryRepository categoryRepository;
	
	@Autowired
	private ArticleRepository articleRepository;

	public List<Article> findByBrand(String brand){
		 return articleRepository.findByBrand(brand);
		
	}
	
	public List<Article> findByBrandContains(String brand){
		return articleRepository.findByBrandContains(brand);
	}
	public List<Article> findByBrandAndPrice(String brand,double price){
		return articleRepository.findByBrandAndPrice(brand,price);
	}
	@Query("select A from Article A where A.brand like %:x% and A.price > :y")
	public List<Article> searchArticles(@Param("x") String brand,@Param("y")double price){
		return articleRepository.searchArticles(brand,price);
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringShopJpaApplication.class, args);
	}

	@Override
	public void run(String...args) throws Exception{
		//categoryRepository.save(new Category("Smartphone"));
		//articleRepository.save(new Article("S9","Samsoule",250));
		//articleRepository.save(new Article("S8","Samsoule",350));
		for(Article article : articleRepository.findByBrand("Samsoule")) {
			System.out.println(article);
		}
		for(Article article:articleRepository.findByBrandContains("soule")){
			System.out.println(article);
		}
		for(Article article:articleRepository.findByBrandAndPrice("Samsoule",250)) {
			System.out.println(article);
		}
		for (Article article: articleRepository.searchArticles("soule", 200)) {
			System.out.println(article);
		}
	}
}