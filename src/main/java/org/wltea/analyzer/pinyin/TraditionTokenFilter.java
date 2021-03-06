package org.wltea.analyzer.pinyin;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import com.luhuiguo.chinese.ChineseUtils;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 獨立的繁體轉換，與IK配合使用，不能與其他分詞配合使用
 */
public class TraditionTokenFilter extends TokenFilter {
  
  private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
  
  protected TraditionTokenFilter(TokenStream input) {
    super(input);
  }

  @Override
  public boolean incrementToken() throws IOException {
    if (input.incrementToken()) {
      String word = ChineseUtils.toSimplified(termAtt.toString());
      termAtt.copyBuffer(word.toCharArray(), 0, word.length());
      return true;
    } else
      return false;
  }
  
}
