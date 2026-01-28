package main.com.radkovich.module_2_3.exception;

public class EntityNotFoundException extends RepositoryException {
    private final String entityType;
    private final Long entityId;

    public EntityNotFoundException(String entityType, Long entityId) {
        super(entityType + " not found with id: " + entityId);
        this.entityType = entityType;
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public Long getEntityId() {
        return entityId;
    }
}
