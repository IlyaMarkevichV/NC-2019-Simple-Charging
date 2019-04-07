package name.backend.service.Impl;

import name.backend.Entities.RoleEntity;
import name.backend.repository.RoleRepository;
import name.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;
    @Override
    public RoleEntity saveRole(RoleEntity role) {
        return repository.save(role);
    }
}
