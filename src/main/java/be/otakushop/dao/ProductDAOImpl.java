package be.otakushop.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import be.otakushop.entities.Product;
import be.otakushop.entities.Serie;
import be.otakushop.entities.Uitgever;

@Repository
class ProductDAOImpl implements ProductDAO {
	private Map<Long, Product> producten = new ConcurrentHashMap<>();
	
	ProductDAOImpl() {
		producten.put(1L, new Product(1L, "Nendoroid Nadeko Sengoku", new Serie(1L, "Bakemonogatari"), 100, new Date(2014, 5, 16), "From the popular anime series 'Bakemonogatari' comes a Nendoroid of the girl with a snake wrapped around her - Nadeko Sengoku! She comes with three expressions including her standard expression, an unsure embarrassed expression and a bright smiling expression! Her trademark large hat as well as the coat she wears on her shoulders are both included and can be attached and removed whenever you like, allowing you to recreate all sorts of different scenes from the series! She also comes with other optional parts including a swimsuit and sitting parts! Be sure to display her with the other characters from the series to fully bring out the Bakemonogatari universe!", new Uitgever(1L, "Good Smile Company"), new BigDecimal(43.77), 2));
		producten.put(2L, new Product(2L, "Nendoroid Shimakaze", new Serie(2L, "Kantai Collection ~Kan Colle~"), 100, new Date(2014, 3, 22), "From 'Kantai Collection ~KanColle~' comes a Nendoroid of Shimakaze, one of the cutest and fastest battleships you'll ever see! Shimakaze includes various optional parts including three of the cute 'Rensouhou-chan' naval artillery cannons! Other optional parts include an alternate body to pose her in her 'half damage' form, torpedo parts to fire on her enemy and even shooting effect parts for her cute little cannons. Recreate the world of KanColle in cute Nendoroid form!", new Uitgever(1L, "Good Smile Company"), new BigDecimal(49.77), 10));
		producten.put(3L, new Product(3L, "Nendoroid Misaka Mikoto", new Serie(3L, "To Aru Kagaku no Railgun S"), 100,new Date(2013, 9, 24), "From the popular anime series 'Toaru Kagaku no Railgun S' comes a Nendoroid of Tokiwadai Middle School's 'Railgun', also known as Mikoto Misaka! She comes complete with a number of cute optional parts which feature her beloved 'Gekota' character - including her schoolbag with a Gekota strap as well as her Gekota themed cellphone! She also comes with electric effect parts for both her head and the front of her arm, allowing you to display her in her element. The coin used for her trademark 'Railgun' attack is also included! All these optional parts can be used to show off the 'Electric Princess' in a range of poses from epic combat scenes to cute embarrassed scenes, allowing fans to decide which they prefer!", new Uitgever(1L, "Good Smile Company"), new BigDecimal(39.81), 0));
		producten.put(4L, new Product(4L, "Nendoroid Saber - Super Moveable Edition", new Serie(4L, "Fate/Stay Night"), 100,new Date(2010, 9, 26), "From 'Fate/stay night' comes another Nendoroid of the main heroine, Saber - but this time she comes as a \"Super Movable Edition\" which has fully posable joints throughout the body for even more poses than before! She comes with three expressions: a smiling face, an angry face and even a face in mid-tantrum. But that is not all - she also comes with her swords Excalibur and Caliburn, as well as replaceable parts to recreate the sword in its \"Invisible Air\" form. It's still the adorable Nendoroid, but the amount of ways to have fun with it have increased with this edition - you can create even more of your favorite scenes from the series now!", new Uitgever(1L, "Good Smile Company"), new BigDecimal(56.82), 1));
		producten.put(5L, new Product(5L, "Nendoroid Miku - Magical Snow Version", new Serie(5L, "Vocaloids"), 100,new Date(2014, 6, 23), "2014 is Snow Miku's 5th anniversary year, and once again designs were added to the piapro website and the most popular was voted for on a live NicoNico Broadcast! The winning design of the 2014 Snow Miku was by dera_fury, who created a playful Snow Miku filled with magical girl charm! The pet to be included was a separate competition, and the selected design was 'Rabbit Yukine' illustrated by nekosumi – the two of them have now been transformed into the 2014 Nendoroid Snow Miku for a Winter that fans are sure to remember!", new Uitgever(1L, "Good Smile Company"), new BigDecimal(54.54), 4));
	}
	
	@Override
	public Iterable<Product> findAll() {
		return producten.values();
	}
	
	@Override
	public Product read(long id) {
		return producten.get(id);
	}
}
