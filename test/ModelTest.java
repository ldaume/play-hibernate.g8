import com.google.common.collect.Lists;
import models.entities.Person;
import org.junit.Test;
import play.db.jpa.JPA;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by leonard on 17.08.15.
 */
public class ModelTest extends TestBase {

  @Test public void testModel() throws Exception {
    int age = 10;
    String name = "Max";

    final Person person = new Person(name, age);
    JPA.withTransaction(() -> {
      JPA.em().persist(person);
    });

    final List<Person> persons = Lists.newArrayList();
    JPA.withTransaction(() -> {
      persons.addAll(JPA.em().createQuery("from Person", Person.class).getResultList());
    });
    assertThat(persons).hasSize(1);
    assertThat(persons.get(0).getName()).isEqualTo(name);
    assertThat(persons.get(0).getAge()).isEqualTo(age);
  }
}
