import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import play.Logger;
import play.db.jpa.JPA;
import play.test.FakeApplication;
import play.test.Helpers;

import javax.persistence.metamodel.EntityType;
import java.util.List;

/**
 * Created by leonard on 24.07.15.
 */
public class TestBase {

  public static FakeApplication app;

  @After public void tearDown() throws Exception {

    JPA.withTransaction(() -> {
      JPA.em().createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

      JPA.em().getMetamodel().getEntities().stream().map(EntityType::getName).forEach(entity -> {
        final List items = JPA.em().createQuery("from " + entity).getResultList();
        Logger.warn("Will delete {} items from {}.", items.size(), entity);
        items.stream().forEach(o -> JPA.em().remove(o));
      });

      JPA.em().createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    });
  }

  @BeforeClass public static void startApp() {
    app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
    Helpers.start(app);
  }

  @AfterClass public static void stopApp() {
    Helpers.stop(app);
  }
}
