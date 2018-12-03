
package infra.data.event;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lieu_quartier",
    "ville",
    "nom",
    "h_lsf",
    "heure_fin",
    "accueil_enfant",
    "lieu",
    "gratuit",
    "info_suppl",
    "precisions_tarifs",
    "h_visuel",
    "lieu_siteweb",
    "heure_debut",
    "location",
    "type",
    "h_intellectuel",
    "lieu_tel",
    "h_psychique",
    "description",
    "emetteur",
    "adresse",
    "rubrique",
    "annule",
    "date",
    "lien_agenda",
    "internet_1",
    "h_hyperacousie",
    "code_postal",
    "media_1",
    "id_manif",
    "h_moteur",
    "url_internet_1",
    "reporte",
    "complet",
    "h_auditif",
    "lieu_email",
    "libelle_festival",
    "precisions_public"
})
public class Fields {

    @JsonProperty("lieu_quartier")
    private String lieuQuartier;
    @JsonProperty("ville")
    private String ville;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("h_lsf")
    private String hLsf;
    @JsonProperty("heure_fin")
    private String heureFin;
    @JsonProperty("accueil_enfant")
    private String accueilEnfant;
    @JsonProperty("lieu")
    private String lieu;
    @JsonProperty("gratuit")
    private String gratuit;
    @JsonProperty("info_suppl")
    private String infoSuppl;
    @JsonProperty("precisions_tarifs")
    private String precisionsTarifs;
    @JsonProperty("h_visuel")
    private String hVisuel;
    @JsonProperty("lieu_siteweb")
    private String lieuSiteweb;
    @JsonProperty("heure_debut")
    private String heureDebut;
    @JsonProperty("location")
    private String location;
    @JsonProperty("type")
    private String type;
    @JsonProperty("h_intellectuel")
    private String hIntellectuel;
    @JsonProperty("lieu_tel")
    private String lieuTel;
    @JsonProperty("h_psychique")
    private String hPsychique;
    @JsonProperty("description")
    private String description;
    @JsonProperty("emetteur")
    private String emetteur;
    @JsonProperty("adresse")
    private String adresse;
    @JsonProperty("rubrique")
    private String rubrique;
    @JsonProperty("annule")
    private String annule;
    @JsonProperty("date")
    private String date;
    @JsonProperty("lien_agenda")
    private String lienAgenda;
    @JsonProperty("internet_1")
    private String internet1;
    @JsonProperty("h_hyperacousie")
    private String hHyperacousie;
    @JsonProperty("code_postal")
    private String codePostal;
    @JsonProperty("media_1")
    private String media1;
    @JsonProperty("id_manif")
    private String idManif;
    @JsonProperty("h_moteur")
    private String hMoteur;
    @JsonProperty("url_internet_1")
    private String urlInternet1;
    @JsonProperty("reporte")
    private String reporte;
    @JsonProperty("complet")
    private String complet;
    @JsonProperty("h_auditif")
    private String hAuditif;
    @JsonProperty("lieu_email")
    private String lieuEmail;
    @JsonProperty("libelle_festival")
    private String libelleFestival;
    @JsonProperty("precisions_public")
    private String precisionsPublic;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("lieu_quartier")
    public String getLieuQuartier() {
        return lieuQuartier;
    }

    @JsonProperty("lieu_quartier")
    public void setLieuQuartier(String lieuQuartier) {
        this.lieuQuartier = lieuQuartier;
    }

    @JsonProperty("ville")
    public String getVille() {
        return ville;
    }

    @JsonProperty("ville")
    public void setVille(String ville) {
        this.ville = ville;
    }

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    @JsonProperty("nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    @JsonProperty("h_lsf")
    public String getHLsf() {
        return hLsf;
    }

    @JsonProperty("h_lsf")
    public void setHLsf(String hLsf) {
        this.hLsf = hLsf;
    }

    @JsonProperty("heure_fin")
    public String getHeureFin() {
        return heureFin;
    }

    @JsonProperty("heure_fin")
    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    @JsonProperty("accueil_enfant")
    public String getAccueilEnfant() {
        return accueilEnfant;
    }

    @JsonProperty("accueil_enfant")
    public void setAccueilEnfant(String accueilEnfant) {
        this.accueilEnfant = accueilEnfant;
    }

    @JsonProperty("lieu")
    public String getLieu() {
        return lieu;
    }

    @JsonProperty("lieu")
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @JsonProperty("gratuit")
    public String getGratuit() {
        return gratuit;
    }

    @JsonProperty("gratuit")
    public void setGratuit(String gratuit) {
        this.gratuit = gratuit;
    }

    @JsonProperty("info_suppl")
    public String getInfoSuppl() {
        return infoSuppl;
    }

    @JsonProperty("info_suppl")
    public void setInfoSuppl(String infoSuppl) {
        this.infoSuppl = infoSuppl;
    }

    @JsonProperty("precisions_tarifs")
    public String getPrecisionsTarifs() {
        return precisionsTarifs;
    }

    @JsonProperty("precisions_tarifs")
    public void setPrecisionsTarifs(String precisionsTarifs) {
        this.precisionsTarifs = precisionsTarifs;
    }

    @JsonProperty("h_visuel")
    public String getHVisuel() {
        return hVisuel;
    }

    @JsonProperty("h_visuel")
    public void setHVisuel(String hVisuel) {
        this.hVisuel = hVisuel;
    }

    @JsonProperty("lieu_siteweb")
    public String getLieuSiteweb() {
        return lieuSiteweb;
    }

    @JsonProperty("lieu_siteweb")
    public void setLieuSiteweb(String lieuSiteweb) {
        this.lieuSiteweb = lieuSiteweb;
    }

    @JsonProperty("heure_debut")
    public String getHeureDebut() {
        return heureDebut;
    }

    @JsonProperty("heure_debut")
    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("h_intellectuel")
    public String getHIntellectuel() {
        return hIntellectuel;
    }

    @JsonProperty("h_intellectuel")
    public void setHIntellectuel(String hIntellectuel) {
        this.hIntellectuel = hIntellectuel;
    }

    @JsonProperty("lieu_tel")
    public String getLieuTel() {
        return lieuTel;
    }

    @JsonProperty("lieu_tel")
    public void setLieuTel(String lieuTel) {
        this.lieuTel = lieuTel;
    }

    @JsonProperty("h_psychique")
    public String getHPsychique() {
        return hPsychique;
    }

    @JsonProperty("h_psychique")
    public void setHPsychique(String hPsychique) {
        this.hPsychique = hPsychique;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("emetteur")
    public String getEmetteur() {
        return emetteur;
    }

    @JsonProperty("emetteur")
    public void setEmetteur(String emetteur) {
        this.emetteur = emetteur;
    }

    @JsonProperty("adresse")
    public String getAdresse() {
        return adresse;
    }

    @JsonProperty("adresse")
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @JsonProperty("rubrique")
    public String getRubrique() {
        return rubrique;
    }

    @JsonProperty("rubrique")
    public void setRubrique(String rubrique) {
        this.rubrique = rubrique;
    }

    @JsonProperty("annule")
    public String getAnnule() {
        return annule;
    }

    @JsonProperty("annule")
    public void setAnnule(String annule) {
        this.annule = annule;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("lien_agenda")
    public String getLienAgenda() {
        return lienAgenda;
    }

    @JsonProperty("lien_agenda")
    public void setLienAgenda(String lienAgenda) {
        this.lienAgenda = lienAgenda;
    }

    @JsonProperty("internet_1")
    public String getInternet1() {
        return internet1;
    }

    @JsonProperty("internet_1")
    public void setInternet1(String internet1) {
        this.internet1 = internet1;
    }

    @JsonProperty("h_hyperacousie")
    public String getHHyperacousie() {
        return hHyperacousie;
    }

    @JsonProperty("h_hyperacousie")
    public void setHHyperacousie(String hHyperacousie) {
        this.hHyperacousie = hHyperacousie;
    }

    @JsonProperty("code_postal")
    public String getCodePostal() {
        return codePostal;
    }

    @JsonProperty("code_postal")
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @JsonProperty("media_1")
    public String getMedia1() {
        return media1;
    }

    @JsonProperty("media_1")
    public void setMedia1(String media1) {
        this.media1 = media1;
    }

    @JsonProperty("id_manif")
    public String getIdManif() {
        return idManif;
    }

    @JsonProperty("id_manif")
    public void setIdManif(String idManif) {
        this.idManif = idManif;
    }

    @JsonProperty("h_moteur")
    public String getHMoteur() {
        return hMoteur;
    }

    @JsonProperty("h_moteur")
    public void setHMoteur(String hMoteur) {
        this.hMoteur = hMoteur;
    }

    @JsonProperty("url_internet_1")
    public String getUrlInternet1() {
        return urlInternet1;
    }

    @JsonProperty("url_internet_1")
    public void setUrlInternet1(String urlInternet1) {
        this.urlInternet1 = urlInternet1;
    }

    @JsonProperty("reporte")
    public String getReporte() {
        return reporte;
    }

    @JsonProperty("reporte")
    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    @JsonProperty("complet")
    public String getComplet() {
        return complet;
    }

    @JsonProperty("complet")
    public void setComplet(String complet) {
        this.complet = complet;
    }

    @JsonProperty("h_auditif")
    public String getHAuditif() {
        return hAuditif;
    }

    @JsonProperty("h_auditif")
    public void setHAuditif(String hAuditif) {
        this.hAuditif = hAuditif;
    }

    @JsonProperty("lieu_email")
    public String getLieuEmail() {
        return lieuEmail;
    }

    @JsonProperty("lieu_email")
    public void setLieuEmail(String lieuEmail) {
        this.lieuEmail = lieuEmail;
    }

    @JsonProperty("libelle_festival")
    public String getLibelleFestival() {
        return libelleFestival;
    }

    @JsonProperty("libelle_festival")
    public void setLibelleFestival(String libelleFestival) {
        this.libelleFestival = libelleFestival;
    }

    @JsonProperty("precisions_public")
    public String getPrecisionsPublic() {
        return precisionsPublic;
    }

    @JsonProperty("precisions_public")
    public void setPrecisionsPublic(String precisionsPublic) {
        this.precisionsPublic = precisionsPublic;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
