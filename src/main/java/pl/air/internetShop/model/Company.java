package pl.air.internetShop.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "companies")
@AttributeOverride(column = @Column(name = "com_id"), name = "id")
@NoArgsConstructor @Getter @Setter
public class Company extends BaseEntity {
	
	@NotBlank
	@Size(max = 50)
	@Column(nullable = false)
	private String name;
	
	@Size(max = 100)
	private String description;

}
