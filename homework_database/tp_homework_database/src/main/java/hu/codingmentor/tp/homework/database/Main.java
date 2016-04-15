package hu.codingmentor.tp.homework.database;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {

    private static EntityManagerFactory factory;

    private static final Logger LOGGER = Logger.getLogger("logger");

    private Main() {
        //default
    }

    
    
    public static void main(String[] args) {

        Calendar temp = Calendar.getInstance();
        temp.set(Calendar.YEAR, 1960);
        Date date = temp.getTime();

        Author author = new Author();
        author.setDateOfBirth(date);
        author.setName("Kanalas Szkájwóker");
        author.setGender(Gender.MALE);

        Author author1 = new Author();
        author1.setDateOfBirth(date);
        author1.setName("Lakatos Ronaldó");
        author1.setGender(Gender.FEMALE);

        Book book1 = new Book();
        book1.setAuthor(author1);
        book1.setTitle("gyűrűk ura");
        book1.getColorType().add(ColorType.RED);
        temp.set(Calendar.YEAR, 1996);
        book1.setDateOfPublish(date);
        book1.setLengthOfItem(LengthOfItems.LONG);

        Article article = new Article();
        article.setAuthor(author1);
        article.setTitle("Harry Potter");
        article.getColorType().add(ColorType.WHITE);
        temp.set(Calendar.YEAR, 1999);
        article.setDateOfPublish(date);
        article.setLengthOfItem(LengthOfItems.SHORT);

        NewsPaper news = new NewsPaper();
        article.setAuthor(author);
        news.setTitle("daily news");
        news.getColorType().add(ColorType.GREEN);
        temp.set(Calendar.YEAR, 2003);
        news.setDateOfPublish(date);
        news.setLengthOfItem(LengthOfItems.SHORT);

        factory = Persistence.createEntityManagerFactory("hu.codingmentor_tp_homework_database_jar_1.0-SNAPSHOTPU");
        EntityManager em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(author);
        em.persist(author1);
        em.persist(book1);
        em.persist(article);
        em.persist(news);

        TypedQuery<Author> authorQueryFEMALE = em.createNamedQuery("QueryForFemaleAuthor", Author.class);
        Author testFemaleQueryAuthor = authorQueryFEMALE.getSingleResult();

        TypedQuery<Author> authorQueryMALE = em.createNamedQuery("QueryForFemaleAuthor", Author.class);
        Author testMaleQueryAuthor = authorQueryMALE.getSingleResult();

        TypedQuery<Book> bookQuery = em.createNamedQuery("QueryBook", Book.class);
        Book testBookQuery = bookQuery.getSingleResult();

        TypedQuery<NewsPaper> newsQuery = em.createNamedQuery("QueryNews", NewsPaper.class);
        NewsPaper testNewsQuery = newsQuery.getSingleResult();

        TypedQuery<Article> articleQuery = em.createNamedQuery("QueryArticle", Article.class);
        Article testArticleQuery = articleQuery.getSingleResult();

        em.getTransaction().commit();

        LOGGER.info("-----------QUERIES-------------");
        LOGGER.info(testFemaleQueryAuthor.toString());
        LOGGER.info(testMaleQueryAuthor.toString());
        LOGGER.info(testBookQuery.toString());
        LOGGER.info(testNewsQuery.toString());
        LOGGER.info(testArticleQuery.toString());

        em.close();
        factory.close();

    }
}
