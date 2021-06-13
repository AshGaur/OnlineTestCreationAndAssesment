package com.testcreation.router.bean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//@Entity(name="admins")
public class Admin {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(updatable = false)
	Integer id;
	
//	@Column(unique=true,nullable=false)
	String email;
	
//	@Column(nullable=false)
	String password;
	
//	public String toString() {
//		return String.format("{ \"email\": \"%s\", \"password\":\"%s\"}", this.getEmail(),this.getPassword());
//	}
}