package info.unterstein.todofx.business.entity;


import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

  public String title;

  public String description;

  public boolean finished;

  public Date dueDate;
}
