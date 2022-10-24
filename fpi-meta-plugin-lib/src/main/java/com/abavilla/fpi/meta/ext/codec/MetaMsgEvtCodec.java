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

package com.abavilla.fpi.meta.ext.codec;

import com.abavilla.fpi.fw.util.MapperUtil;
import com.abavilla.fpi.meta.ext.dto.msgr.ext.MetaMsgEvtDto;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import lombok.SneakyThrows;

public class MetaMsgEvtCodec implements MessageCodec<MetaMsgEvtDto, MetaMsgEvtDto> {

  @SneakyThrows
  @Override
  public void encodeToWire(Buffer buffer, MetaMsgEvtDto metaMsgEvtDto) {
    ObjectWriter writer = MapperUtil.mapper().writerFor(MetaMsgEvtDto.class);
    byte[] bytes = writer.writeValueAsBytes(metaMsgEvtDto);
    buffer.appendInt(bytes.length);
    buffer.appendBytes(bytes);
  }

  @SneakyThrows
  @Override
  public MetaMsgEvtDto decodeFromWire(int pos, Buffer buffer) {
    // My custom message starting from this *position* of buffer

    // Length of JSON
    int length = buffer.getInt(pos);

    // Get JSON string by it`s length
    // Jump 4 because getInt() == 4 bytes
    pos += 4;
    byte[] bytes = buffer.getBytes(pos, pos + length);

    ObjectReader reader = MapperUtil.mapper().readerFor(MetaMsgEvtDto.class);
    return reader.readValue(bytes);
  }

  @Override
  public MetaMsgEvtDto transform(MetaMsgEvtDto metaMsgEvtDto) {
    return metaMsgEvtDto;
  }

  @Override
  public String name() {
    return this.getClass().getName();
  }

  @Override
  public byte systemCodecID() {
    return -1;
  }
}
