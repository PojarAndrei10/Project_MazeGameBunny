package view.interf;

import java.util.List;

public interface AdminInterface {
    String getLegalName();
    String getisAdmin();
    String getUsername();
    String getPassword();
    public List<String> getUsers();
    public boolean deleteUser();
    public boolean createUser();
    public boolean updateUser();
}
