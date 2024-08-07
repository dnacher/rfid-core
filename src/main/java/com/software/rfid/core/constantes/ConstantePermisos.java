package com.software.rfid.core.constantes;

public enum ConstantePermisos {

    PERMISO_VER("", 1, 9),
    PERMISO_OPERADOR("Operador",3, 11),
    PERMISO_ADMIN("Admin", 7, 15);

    private final int permisoValorSinComision;
    private final int permisoValorConComision;
    private final String permisoNombre;

    ConstantePermisos(String permisoNombre, int permisoValorSinComision, int permisoValorConComision) {
        this.permisoNombre = permisoNombre;
        this.permisoValorSinComision = permisoValorSinComision;
        this.permisoValorConComision = permisoValorConComision;
    }

    public String getPermisoNombre() {
        return permisoNombre;
    }

    public int getPermisoValorSinComision() {
        return permisoValorSinComision;
    }

    public int getPermisoValorConComision() {
        return permisoValorConComision;
    }
}
