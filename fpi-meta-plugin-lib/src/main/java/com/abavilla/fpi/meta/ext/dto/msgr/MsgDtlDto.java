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

package com.abavilla.fpi.meta.ext.dto.msgr;

import java.util.List;

import com.abavilla.fpi.fw.dto.AbsFieldDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Data transfer object containing the details of the contents of the Facebook Messenger message
 *
 * @author <a href="mailto:vincevillamora@gmail.com">Vince Villamora</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@RegisterForReflection
public class MsgDtlDto extends AbsFieldDto {

  /**
   * Message id
   */
  private String mid;

  /**
   * Textual contents of the message
   */
  private String text;

  /**
   * If the message is a reply, this field will contain the details about it
   */
  @JsonProperty("reply_to")
  private MsgDtlDto replyTo;

  private List<MsgAttchmtDto> attachments;

  @JsonProperty("quick_reply")
  private QuickReplyDto quickReply;
}
