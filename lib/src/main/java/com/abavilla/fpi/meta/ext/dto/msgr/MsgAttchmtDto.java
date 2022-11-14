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

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@RegisterForReflection
public class MsgAttchmtDto extends AbsFieldDto {

  private String type;
  private PayloadDto payload;

  @Data
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @RegisterForReflection
  public static class PayloadDto extends AbsFieldDto {

    private String url;
    private String title;
    @JsonProperty("sticker_id")
    private String stickerId;
    private Product product;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @RegisterForReflection
    public static class Product extends AbsFieldDto {

      private List<Element> elements;

      @Data
      @EqualsAndHashCode(callSuper = true)
      @NoArgsConstructor
      @RegisterForReflection
      public static class Element extends AbsFieldDto {
        private String id;
        @JsonProperty("retailer_id")
        private String retailerId;
        @JsonProperty("image_url")
        private String imageUrl;
        private String title;
        private String subtitle;
      }

    }

  }
}
