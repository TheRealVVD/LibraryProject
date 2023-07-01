//package library.project.mvc.dao;
//
//import library.project.mvc.config.SpringConfig;
//import library.project.mvc.models.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.filter.HiddenHttpMethodFilter;
//import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//
//import javax.servlet.DispatcherType;
//import javax.servlet.FilterRegistration;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.sql.DataSource;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.EnumSet;
//import java.util.Objects;
//
//public class PersonMapper implements RowMapper<Person> {
//
//    @Override
//    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Person person = new Person();
//
//        person.setId(rs.getInt("id"));
//        person.setName(rs.getString("name"));
//        person.setSurname(rs.getString("surname"));
//        person.setPatronymic(rs.getString("patronymic"));
//        person.setYearOfBirthday(rs.getInt("year"));
//
//        return person;
//    }
//}
//
//
//
