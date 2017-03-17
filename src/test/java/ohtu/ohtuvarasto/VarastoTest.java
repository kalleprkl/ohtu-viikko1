package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void testaaToString() {
        varasto.lisaaVarastoon(8);
        
        assertEquals("saldo = 8.0, vielä tilaa 2.0", varasto.toString());
        
    }
    
    @Test
    public void konstruktoriEiHuoliNegatiivistaTilavuutta() {
        Varasto v = new Varasto(-1);
        
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriEiHuoliNegatiivistaTilavuutta() {
        Varasto v = new Varasto(-1, 0);
        
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriEiHuoliNegatiivistaAlkusaldoa() {
        Varasto v = new Varasto(10, -1);
        
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriAntaaTilavuudenAlkusaldonaJosLiianIso() {
        Varasto v = new Varasto(10, 11);
        
        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuTilavuusAsettuu() {
        Varasto v = new Varasto(10, 5);
        
        assertEquals(10, v.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuAlkusaldoAsettuu() {
        Varasto v = new Varasto(10, 5);
        
        assertEquals(5, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiLisaaNegatiivista() {
        varasto.lisaaVarastoon(8);
        
        varasto.lisaaVarastoon(-1);
        
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiTaytaYli() {
        varasto.lisaaVarastoon(8);
        
        varasto.lisaaVarastoon(30);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiOtaNegatiivista() {
        varasto.lisaaVarastoon(8);
        
        double ote = varasto.otaVarastosta(-1);
        
        assertEquals(0, ote, vertailuTarkkuus);
    }
    
    @Test
    public void saldoNollaaJosOttaaLiikaa() {
        varasto.lisaaVarastoon(8);
        
        varasto.otaVarastosta(9);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void antaaKaikenMitaVoi() {
        varasto.lisaaVarastoon(8);
        
        double ote = varasto.otaVarastosta(18);
        
        assertEquals(8, ote, vertailuTarkkuus);
    }
}