package com.rensen.techiteasykotlin

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime


// Voor Entities gebruik je geen "data class" (dat is als een Record)
// Voor Entities heb je een default constructor nodig.
@Entity
class Television {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var type: String? = null
    var brand: String? = null
    var name: String? = null
    var screenType: String? = null
    var screenQuality: String? = null
    var price: Double? = null
    var availableSize: Double? = null
    var refreshRate: Double? = null
    var smartTv: Boolean? = null
    var wifi: Boolean? = null
    var voiceControl: Boolean? = null
    var hdr: Boolean? = null
    var blueTooth: Boolean? = null
    var ambiLight: Boolean? = null
    var originalStock: Int? = null
    var sold: Int? = null
    @OneToOne
    var remoteController: RemoteController? = null
    @ManyToOne
    var ciModule: CiModule? = null
    @ManyToMany
    var wallBrackets: List<WallBracket>? = null

    constructor(
        type: String?,
        brand: String?,
        name: String?,
        screenType: String?,
        screenQuality: String?,
        price: Double?,
        availableSize: Double?,
        refreshRate: Double?,
        smartTv: Boolean?,
        wifi: Boolean?,
        voiceControl: Boolean?,
        hdr: Boolean?,
        blueTooth: Boolean?,
        ambiLight: Boolean?,
        originalStock: Int?,
        sold: Int?
    ) {
        this.type = type
        this.brand = brand
        this.name = name
        this.screenType = screenType
        this.screenQuality = screenQuality
        this.price = price
        this.availableSize = availableSize
        this.refreshRate = refreshRate
        this.smartTv = smartTv
        this.wifi = wifi
        this.voiceControl = voiceControl
        this.hdr = hdr
        this.blueTooth = blueTooth
        this.ambiLight = ambiLight
        this.originalStock = originalStock
        this.sold = sold
    }
    constructor()
}

@Entity
class RemoteController{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null
     var compatibleWith: String? = null
     var batteryType: String? = null
     var name: String? = null
     var brand: String? = null
     var price: Double? = null
     var originalStock: Int? = null

    @OneToOne(mappedBy = "remoteController")
    var television: Television? = null

    constructor()
    constructor(
        id: Long?,
        compatibleWith: String?,
        batteryType: String?,
        name: String?,
        brand: String?,
        price: Double?,
        originalStock: Int?
    ) {
        this.id = id
        this.compatibleWith = compatibleWith
        this.batteryType = batteryType
        this.name = name
        this.brand = brand
        this.price = price
        this.originalStock = originalStock
    }
}

@Entity
class WallBracket{
    constructor()
    constructor(
        id: Long?,
        size: String?,
        adjustable: Boolean?,
        name: String?,
        price: Double?,
        televisions: List<Television>?
    ) {
        this.id = id
        this.size = size
        this.adjustable = adjustable
        this.name = name
        this.price = price
        this.televisions = televisions
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     var id: Long? = null
     var size: String? = null
     var adjustable: Boolean? = null
     var name: String? = null
     var price: Double? = null

    @ManyToMany(mappedBy = "wallBrackets")
    var televisions: List<Television>? = null

}

@Entity
class CiModule{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     var id: Long? = null
     var name: String? = null
     var type: String? = null
     var price: Double? = null

    @OneToMany(mappedBy = "ciModule")
    @JsonIgnore
    var televisions: List<Television>? = null

    constructor(id: Long?, name: String?, type: String?, price: Double?, televisions: List<Television>?) {
        this.id = id
        this.name = name
        this.type = type
        this.price = price
        this.televisions = televisions
    }
}

@Entity
@Table(name = "users")
class User (var userName: String, @get:JvmName("getPasswordOrNull") var password: String) : UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var createdDate: LocalDateTime = LocalDateTime.now()

    var editedDate: LocalDateTime = LocalDateTime.now()

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles : List<Role> = mutableListOf()

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        roles.mapTo( mutableListOf<GrantedAuthority>()) { SimpleGrantedAuthority(it.roleName) }


    override fun getPassword(): String = password?:""
    override fun getUsername(): String = username?:""
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true

}

@Entity
@Table(name = "roles")
class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var active: Boolean = true

    var createdDate: LocalDateTime = LocalDateTime.now()

    var editedDate: LocalDateTime = LocalDateTime.now()

    var description: String? = null;

    var roleName: String? = null
}

