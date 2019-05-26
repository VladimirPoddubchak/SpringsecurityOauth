package com.poddubchak.oauth.Forms;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 09.05.2019
 * Role
 *
 * @author Poddubchak Vladimir
 * @version v1.0
 */

@Data
@AllArgsConstructor
public class UserForm {
    private String username;
    private String password;
}