package com.dschepkin.library.model;

import javax.persistence.*;

@Entity
@Table(name="role_privs")
public class Role_priv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne //FK (owning site)
    @JoinColumn(name="role_id", referencedColumnName = "id")
//    @Column(name="role_id")
    private Role role;

    @ManyToOne //FK (owning site)
    @JoinColumn(name="priv_id",referencedColumnName = "id")
//    @Column(name="priv_id")
    private Privilege privilege;

    public Role_priv() {
    }

    public Role_priv(Role role, Privilege privilege) {
        this.role = role;
        this.privilege = privilege;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}
