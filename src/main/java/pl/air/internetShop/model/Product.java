package pl.air.internetShop.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@AttributeOverride(column = @Column(name = "pro_id"), name = "id")
@NoArgsConstructor @Getter @Setter
public class Product extends BaseEntity {
	
	@NotBlank
	@Size(max = 100)
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@NotBlank
	@Size(max = 100)
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "release_date")
	private LocalDate releaseDate;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 7, fraction = 2)
	private BigDecimal price;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "com_id")
	private Company company;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cat_id")
	private Category category;
	
}
