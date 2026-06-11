// Eager loading instantiates or fetches dependent data immediately when the parent object is created,
// while Lazy loading delays that instantiation until the dependent data is explicitly requested.

// Heavy object that takes a long time or lots of memory to load
class HeavyDatabaseService {
    public HeavyDatabaseService() {
        System.out.println("Loading heavy configurations... (Eager)");
    }
    public void fetchData() {
        System.out.println("Processing data...");
    }
}
// Result: HeavyDatabaseService is initialized the moment new AppDashboard() is called,
// even if the application never invokes getDbService().
class AppDashboard {
    // Eagerly instantiated right away
    private final HeavyDatabaseService dbService = new HeavyDatabaseService();

    public HeavyDatabaseService getDbService() {
        return dbService;
    }
}

// Result: If new LazyAppDashboard() is constructed but getDbService() is never called,
// the heavy service is never loaded into memory, saving resources
class LazyAppDashboard {
    // Kept null until explicitly needed
    private HeavyDatabaseService dbService;

    public HeavyDatabaseService getDbService() {
        // Double-checked locking or simple null check depending on thread safety needs
        if (dbService == null) {
            System.out.println("First request received. Initializing dependency...");
            dbService = new HeavyDatabaseService();
        }
        return dbService;
    }
}

public class EagerLoadingAndLazyLoading {
    static void main() {

    }
}
