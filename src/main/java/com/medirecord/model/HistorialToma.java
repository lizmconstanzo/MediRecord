package com.medirecord.model;

import java.sql.Timestamp;

public class HistorialToma {
    private int id;
    private int tratamientoId;
    private Timestamp fechaHoraProgramada;
    private Timestamp fechaHoraReal;
    private String estado;

    public HistorialToma() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTratamientoId() {
        return tratamientoId;
    }

    public void setTratamientoId(int tratamientoId) {
        this.tratamientoId = tratamientoId;
    }

    public Timestamp getFechaHoraProgramada() {
        return fechaHoraProgramada;
    }

    public void setFechaHoraProgramada(Timestamp fechaHoraProgramada) {
        this.fechaHoraProgramada = fechaHoraProgramada;
    }

    public Timestamp getFechaHoraReal() {
        return fechaHoraReal;
    }

    public void setFechaHoraReal(Timestamp fechaHoraReal) {
        this.fechaHoraReal = fechaHoraReal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}