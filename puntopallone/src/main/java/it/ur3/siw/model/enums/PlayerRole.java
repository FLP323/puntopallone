package it.ur3.siw.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PlayerRole {
    // Portieri
    GOALKEEPER(RoleCategory.GOALKEEPER, "GK", "Portiere"),

    // Difensori
    CENTRE_BACK(RoleCategory.DEFENDER, "CB", "Difensore centrale"),
    SWEEPER(RoleCategory.DEFENDER, "SW", "Libero"),
    LEFT_BACK(RoleCategory.DEFENDER, "LB", "Terzino sinistro"),
    RIGHT_BACK(RoleCategory.DEFENDER, "RB", "Terzino destro"),
    WING_BACK_LEFT(RoleCategory.DEFENDER, "LWB", "Laterale sinistro"),
    WING_BACK_RIGHT(RoleCategory.DEFENDER, "RWB", "Laterale destro"),

    // Centrocampisti
    DEFENSIVE_MIDFIELDER(RoleCategory.MIDFIELDER, "CDM", "Centrocampista difensivo (mediano)"),
    CENTRAL_MIDFIELDER(RoleCategory.MIDFIELDER, "CM", "Centrocampista centrale"),
    BOX_TO_BOX(RoleCategory.MIDFIELDER, "BBM", "Mezzala (box-to-box)"),
    DEEP_LYING_PLAYMAKER(RoleCategory.MIDFIELDER, "DLP", "Regista"),
    ATTACKING_MIDFIELDER(RoleCategory.MIDFIELDER, "CAM", "Trequartista"),
    LEFT_MIDFIELDER(RoleCategory.MIDFIELDER, "LM", "Ala sinistra (centrocampo)"),
    RIGHT_MIDFIELDER(RoleCategory.MIDFIELDER, "RM", "Ala destra (centrocampo)"),

    // Attaccanti
    STRIKER(RoleCategory.FORWARD, "ST", "Punta centrale"),
    SECOND_STRIKER(RoleCategory.FORWARD, "SS", "Seconda punta"),
    FALSE_NINE(RoleCategory.FORWARD, "F9", "Falso nove"),
    WINGER_LEFT(RoleCategory.FORWARD, "LW", "Ala sinistra offensiva"),
    WINGER_RIGHT(RoleCategory.FORWARD, "RW", "Ala destra offensiva");

    private final RoleCategory category;
    private final String code;        // abbreviazione classica (FM style)
    private final String description; // descrizione in italiano

    PlayerRole(RoleCategory category, String code, String description) {
        this.category = category;
        this.code = code;
        this.description = description;
    }

    public RoleCategory getCategory() { return category; }
    public String getCode() { return code; }
    public String getDescription() { return description; }

    // Tutti i ruoli di una certa categoria
    public static List<PlayerRole> byCategory(RoleCategory category) {
        return Arrays.stream(values())
                .filter(role -> role.category == category)
                .collect(Collectors.toList());
    }
}
