package com.badillosoft.security;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface CredentialRepository extends CrudRepository<Credential, Long> {
	
	@Query(value="select replace(uuid(),'-','')", nativeQuery=true)
    public String randomToken();

    @Query(value="select * from credential where email=:email and password=:password limit 1", nativeQuery=true)
    public Optional<Credential> checkCredential(@Param("email") String email, @Param("password") String password);

    @Query(value="select * from credential where email=:email and password=:password and role=:role limit 1", nativeQuery=true)
    public Optional<Credential> checkCredentialAndRole(@Param("email") String email, @Param("password") String password, @Param("role") String role);

    @Modifying
    @Query(value="update credential set last_login=now(), last_update=now(), login=1, token=replace(uuid(),'-','') where email=:email", nativeQuery=true)
    public void doLogin(@Param("email") String email);

    @Modifying
    @Query(value="update credential set login=0, token=replace(uuid(),'-','') where email=:email", nativeQuery=true)
    public void doLogout(@Param("email") String email);

    @Query(value="select * from credential where email=:email and token=:token limit 1", nativeQuery=true)
    public Optional<Credential> checkToken(@Param("email") String email, @Param("token") String token);

    @Query(value="select * from credential where email=:email and token=:token and time_to_sec(timediff(now(), last_update))<=:seconds limit 1", nativeQuery=true)
    public Optional<Credential> checkTokenWithExpiration(@Param("email") String email, @Param("token") String token, @Param("seconds") Long seconds);

    @Query(value="select * from credential where email=:email and token=:token and device=:device limit 1", nativeQuery=true)
    public Optional<Credential> checkTokenWithDevice(@Param("email") String email, @Param("token") String token, @Param("device") String device);

    @Query(value="select * from credential where email=:email and token=:token and device=:device and time_to_sec(timediff(now(), last_update))<=:seconds limit 1", nativeQuery=true)
    public Optional<Credential> checkTokenWithDeviceAndExpiration(@Param("email") String email, @Param("token") String token, @Param("device") String device, @Param("seconds") Long seconds);

}
