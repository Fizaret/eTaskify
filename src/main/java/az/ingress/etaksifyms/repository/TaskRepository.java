package az.ingress.etaksifyms.repository;

import az.ingress.etaksifyms.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository  extends JpaRepository<Task,Long> {

    @Query("select t from Task t " +
            "join User u on t.user = u " +
            "join Organization o on u.organization = o " +
            "where o.id = :id")

    List<Task> findAllTasksByUsersId(Long organizationId);

    @Query(value = "select t from Task t " +
            "join t.user u " +
            "join u.organization o " +
            "where o.id = :id")
    List<Task> findAllTasksByOrganizationId(Long organizationId);

}