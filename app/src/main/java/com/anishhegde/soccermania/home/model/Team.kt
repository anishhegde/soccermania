package com.anishhegde.soccermania.home.model

import android.os.Parcel
import android.os.Parcelable

data class Team(
	val intStadiumCapacity: String? = null,
	val strTeamShort: String? = null,
	val strSport: String? = null,
	val strDescriptionCN: String? = null,
	val strTeamJersey: String? = null,
	val strTeamFanart2: String? = null,
	val strTeamFanart3: String? = null,
	val strTeamFanart4: String? = null,
	val strStadiumDescription: String? = null,
	val strTeamFanart1: String? = null,
	val intLoved: String? = null,
	val idLeague: String? = null,
	val idSoccerXML: String? = null,
	val strTeamLogo: String? = null,
	val strDescriptionSE: String? = null,
	val strDescriptionJP: String? = null,
	val strDescriptionFR: String? = null,
	val strStadiumLocation: String? = null,
	val strDescriptionNL: String? = null,
	val strCountry: String? = null,
	val strRSS: String? = null,
	val strDescriptionRU: String? = null,
	val strTeamBanner: String? = null,
	val strDescriptionNO: String? = null,
	val strStadiumThumb: String? = null,
	val strDescriptionES: String? = null,
	val intFormedYear: String? = null,
	val strInstagram: String? = null,
	val strDescriptionIT: String? = null,
	val idTeam: String? = null,
	val strDescriptionEN: String? = null,
	val strWebsite: String? = null,
	val strYoutube: String? = null,
	val strDescriptionIL: String? = null,
	val idAPIfootball: String? = null,
	val strLocked: String? = null,
	val strAlternate: String? = null,
	val strTeam: String? = null,
	val strTwitter: String? = null,
	val strDescriptionHU: String? = null,
	val strGender: String? = null,
	val strDivision: String? = null,
	val strStadium: String? = null,
	val strFacebook: String? = null,
	val strTeamBadge: String? = null,
	val strDescriptionPT: String? = null,
	val strDescriptionDE: String? = null,
	val strLeague: String? = null,
	val strManager: String? = null,
	val strKeywords: String? = null,
	val strDescriptionPL: String? = null
) : Parcelable {
	constructor(source: Parcel) : this(
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString(),
		source.readString()
	)

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
		writeString(intStadiumCapacity)
		writeString(strTeamShort)
		writeString(strSport)
		writeString(strDescriptionCN)
		writeString(strTeamJersey)
		writeString(strTeamFanart2)
		writeString(strTeamFanart3)
		writeString(strTeamFanart4)
		writeString(strStadiumDescription)
		writeString(strTeamFanart1)
		writeString(intLoved)
		writeString(idLeague)
		writeString(idSoccerXML)
		writeString(strTeamLogo)
		writeString(strDescriptionSE)
		writeString(strDescriptionJP)
		writeString(strDescriptionFR)
		writeString(strStadiumLocation)
		writeString(strDescriptionNL)
		writeString(strCountry)
		writeString(strRSS)
		writeString(strDescriptionRU)
		writeString(strTeamBanner)
		writeString(strDescriptionNO)
		writeString(strStadiumThumb)
		writeString(strDescriptionES)
		writeString(intFormedYear)
		writeString(strInstagram)
		writeString(strDescriptionIT)
		writeString(idTeam)
		writeString(strDescriptionEN)
		writeString(strWebsite)
		writeString(strYoutube)
		writeString(strDescriptionIL)
		writeString(idAPIfootball)
		writeString(strLocked)
		writeString(strAlternate)
		writeString(strTeam)
		writeString(strTwitter)
		writeString(strDescriptionHU)
		writeString(strGender)
		writeString(strDivision)
		writeString(strStadium)
		writeString(strFacebook)
		writeString(strTeamBadge)
		writeString(strDescriptionPT)
		writeString(strDescriptionDE)
		writeString(strLeague)
		writeString(strManager)
		writeString(strKeywords)
		writeString(strDescriptionPL)
	}

	companion object {
		@JvmField
		val CREATOR: Parcelable.Creator<Team> = object : Parcelable.Creator<Team> {
			override fun createFromParcel(source: Parcel): Team = Team(source)
			override fun newArray(size: Int): Array<Team?> = arrayOfNulls(size)
		}
	}
}
