package name.backend.service.Impl;

import name.backend.repository.RoleRepository;
import name.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;

    @Override
    public List getAll() {
        return repository.findAllByRoleIdGreaterThan(1);
    }
}
