package Management.common;

public enum Role {
    ADMIN(1),
    CLIENT(2);

    private int id;
    Role(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public static Role fromID(int id) {
        Role role = null;
        for (Role r:values()) {
            if (r.getId() == id) {
                role = r;
                break;
            }
        }
        return role;
    }
}
