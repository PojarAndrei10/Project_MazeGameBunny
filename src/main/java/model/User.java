package model;


public class User {
    private Integer idPlayer;
    private String legalName,username,password;
    private Boolean isAdmin;
    private Integer score;
    private Integer scorelevel1;
    private Integer scorelevel2;
    private Integer scorelevel3;

    public User(Integer id, String legalName, Boolean isAdmin, String username, String password, Integer totalScore) {
        this.idPlayer = id;
        this.legalName = legalName;
        this.isAdmin = isAdmin;
        this.username = username;
        this.password = password;
        this.score = totalScore;
    }
    public User(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getLegalName() {
        return legalName;
    }
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
    public Boolean getAdmin() {
        return isAdmin;
    }
    public void setAdmin(Boolean admin) {
        this.isAdmin = admin;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public Integer getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Integer id) {
        this.idPlayer = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer totalScore) {
        this.score = totalScore;
    }
    //pt tabelul pt admini
    public String toString2() {
        return "Gamer{" +
                "id=" + idPlayer +
                ", legalName='" + legalName + '\'' +
                ", isAdmin=" + isAdmin +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    @Override
    public String toString() {
        return "Gamer{" +
                "id=" + idPlayer +
                ", legalName='" + legalName + '\'' +
                ", isAdmin=" + isAdmin +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", totalScore=" + score +
                '}';
    }
}

