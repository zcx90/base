/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.forkjoin.core.net.message.coder;

import org.forkjoin.core.net.message.Packet;
import org.forkjoin.core.net.CrcEncryptChannelHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class CrcEncryptPacketEncoder extends MessageToByteEncoder<Packet> {

    public CrcEncryptPacketEncoder() {

    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        try {
            CrcEncryptCoder coder = ctx.channel().attr(CrcEncryptChannelHandler.CRC_ENCRYPT).get();
            coder.write(out, msg);
        } catch (Throwable t) {
            ctx.fireExceptionCaught(t);
        }
    }
}
