package com.marcosledesma.ejercicio01edu.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Ordenador implements Parcelable {
    private String marca;
    private String modelo;
    private int ram;
    private float tamPantalla;
    private Date fechaCompra;

    public Ordenador(String marca, String modelo, int ram, float tamPantalla, Date fechaCompra) {
        this.marca = marca;
        this.modelo = modelo;
        this.ram = ram;
        this.tamPantalla = tamPantalla;
        this.fechaCompra = fechaCompra;
    }

    public Ordenador(String marca, String modelo, Date fechaCompra) {
        this.marca = marca;
        this.modelo = modelo;
        this.fechaCompra = fechaCompra;
    }

    protected Ordenador(Parcel in) {
        marca = in.readString();
        modelo = in.readString();
        ram = in.readInt();
        tamPantalla = in.readFloat();
        // Añadir fecha de esta manera
        fechaCompra = new Date();   // Instanciar Date para que no explote
        fechaCompra.setTime(in.readLong());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeString(modelo);
        dest.writeInt(ram);
        dest.writeFloat(tamPantalla);
        // Pasar a String el objeto fecha (ya que Date no es parcelable)
        dest.writeLong(fechaCompra.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ordenador> CREATOR = new Creator<Ordenador>() {
        @Override
        public Ordenador createFromParcel(Parcel in) {
            return new Ordenador(in);
        }

        @Override
        public Ordenador[] newArray(int size) {
            return new Ordenador[size];
        }
    };

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public float getTamPantalla() {
        return tamPantalla;
    }

    public void setTamPantalla(float tamPantalla) {
        this.tamPantalla = tamPantalla;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }



}
