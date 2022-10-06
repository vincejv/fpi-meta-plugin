/******************************************************************************
 * FPI Application - Abavilla                                                 *
 * Copyright (C) 2022  Vince Jerald Villamora                                 *
 *                                                                            *
 * This program is free software: you can redistribute it and/or modify       *
 * it under the terms of the GNU General Public License as published by       *
 * the Free Software Foundation, either version 3 of the License, or          *
 * (at your option) any later version.                                        *
 *                                                                            *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
 * GNU General Public License for more details.                               *
 *                                                                            *
 * You should have received a copy of the GNU General Public License          *
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.     *
 ******************************************************************************/

package com.abavilla.fpi.meta.mapper;

import java.util.ArrayList;
import java.util.List;

import com.abavilla.fpi.fw.mapper.IMapper;
import com.abavilla.fpi.fw.util.DateUtil;
import com.abavilla.fpi.meta.dto.MetaHookEvtDto;
import com.abavilla.fpi.meta.dto.msgr.EntryDto;
import com.abavilla.fpi.meta.dto.msgr.MessagingDto;
import com.abavilla.fpi.meta.dto.msgr.MetaMsgEvtDto;
import com.abavilla.fpi.meta.dto.msgr.MsgDtlDto;
import com.abavilla.fpi.meta.dto.msgr.ProfileDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MetaHookEvtMapper extends IMapper {

  default List<MetaMsgEvtDto> hookToDtoList(MetaHookEvtDto dto) {
    int listSize = dto.getEntry().stream()
        .mapToInt(outer -> outer.getMessaging().size()).sum();
    List<MetaMsgEvtDto> metaMsgEvtList = new ArrayList<>(listSize);
    for (EntryDto entryDto : dto.getEntry()) {
      for (MessagingDto messagingDto : entryDto.getMessaging()) {
        MetaMsgEvtDto evt = mapMsgDtl(messagingDto.getMessage());
        evt.setSender(profileDto(messagingDto.getSender()));
        evt.setRecipient(profileDto(messagingDto.getRecipient()));
        evt.setTimestamp(DateUtil.convertLdtToStr(
            DateUtil.fromEpoch(messagingDto.getTimestamp())));
        metaMsgEvtList.add(evt);
      }
    }
    return metaMsgEvtList;
  }

  @Mappings(value = {
      @Mapping(target = "metaMsgId", source = "mid"),
      @Mapping(target = "content", source = "text"),
  })
  MetaMsgEvtDto mapMsgDtl(MsgDtlDto dto);

  default String profileDto(ProfileDto dto) {
    if (dto == null) {
      return null;
    }
    return dto.getId();
  }

  default String replyTo(MsgDtlDto dto) {
    if (dto == null) {
      return null;
    }
    return dto.getMid();
  }

}
