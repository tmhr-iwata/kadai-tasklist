package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "task")
@NamedQueries({
    @NamedQuery(
            name = "getAllTasks",
            query = "SELECT m FROM Task AS m ORDER BY m.completed , m.limitday DESC"
            ), // 全件表示の場合は未完了タスクを上に、完了済みは下にして期日降順
    @NamedQuery(
            name = "getCompletedTasks",
            query = "SELECT m FROM Task AS m WHERE m.completed = true ORDER BY m.limitday DESC"
            ),
    @NamedQuery(
            name = "getUncompletedTasks",
            query = "SELECT m FROM Task AS m WHERE m.completed = false ORDER BY m.limitday DESC"
            ),
    @NamedQuery(
            name = "getTasksCount",
            query = "SELECT COUNT(m) FROM Task AS m"
            ),
    @NamedQuery(
            name = "getUncompletedTasksCount",
            query = "SELECT COUNT(m) FROM Task AS m WHERE m.completed = false"
            ),
    @NamedQuery(
            name = "getCompletedTasksCount",
            query = "SELECT COUNT(m) FROM Task AS m WHERE m.completed = true"
            )
})

public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", length = 255, nullable = false)
    private String title; // タスク名

    @Column(name = "content", length = 255, nullable = false)
    private String content; // タスク詳細

    @Column(name = "limitday")
    private Timestamp limitday; // タスクの締め切り日

    @Column(name = "completed", nullable = false)
    private Boolean completed; // falseで未了タスク、trueで完了タスク

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getLimitday() {
        return limitday;
    }

    public void setLimitday(Timestamp limitday) {
        this.limitday = limitday;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

}
