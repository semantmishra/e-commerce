package Beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BrandingBean {
	
	int id;

	String brand_name,brand_logo,domain_name, email, facebook_url, twitter_url,address, phone, about_us, privacy_policy, cookies_policy, terms_policy;
	InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getBrand_logo() {
		return brand_logo;
	}
	public void setBrand_logo(String brand_logo) {
		this.brand_logo = brand_logo;
	}
	public String getDomain_name() {
		return domain_name;
	}
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFacebook_url() {
		return facebook_url;
	}
	public void setFacebook_url(String facebook_url) {
		this.facebook_url = facebook_url;
	}
	public String getTwitter_url() {
		return twitter_url;
	}
	public void setTwitter_url(String twitter_url) {
		this.twitter_url = twitter_url;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAbout_us() {
		return about_us;
	}
	public void setAbout_us(String about_us) {
		this.about_us = about_us;
	}
	public String getPrivacy_policy() {
		return privacy_policy;
	}
	public void setPrivacy_policy(String privacy_policy) {
		this.privacy_policy = privacy_policy;
	}
	public String getCookies_policy() {
		return cookies_policy;
	}
	public void setCookies_policy(String cookies_policy) {
		this.cookies_policy = cookies_policy;
	}
	public String getTerms_policy() {
		return terms_policy;
	}
	public void setTerms_policy(String terms_policy) {
		this.terms_policy = terms_policy;
	}
	
	@Override
	public String toString() {
		return "BrandingBean [id=" + id + ", brand_name=" + brand_name + ", brand_logo=" + brand_logo + ", domain_name="
				+ domain_name + ", email=" + email + ", facebook_url=" + facebook_url + ", twitter_url=" + twitter_url
				+ ", address=" + address + ", phone=" + phone + ", about_us=" + about_us + ", privacy_policy="
				+ privacy_policy + ", cookies_policy=" + cookies_policy + ", terms_policy=" + terms_policy
				+ ", inputStream=" + inputStream + "]";
	}

}
