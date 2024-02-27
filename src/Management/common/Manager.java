package Management.common;

import Management.clients.Client;

import java.util.List;

public interface Manager {
     List<Model> getList();

     Model get(int id);

     boolean insert(Model model);

     boolean update(Model model);

     boolean delete(int id);
}
