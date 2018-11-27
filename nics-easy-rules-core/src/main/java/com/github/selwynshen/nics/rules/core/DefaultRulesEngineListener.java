/**
 * The MIT License
 *
 *  Copyright (c) 2018, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package com.github.selwynshen.nics.rules.core;

import com.github.selwynshen.nics.rules.api.Facts;
import com.github.selwynshen.nics.rules.api.Rule;
import com.github.selwynshen.nics.rules.api.Rules;
import com.github.selwynshen.nics.rules.api.RulesEngineListener;
import com.github.selwynshen.nics.rules.api.Facts;
import com.github.selwynshen.nics.rules.api.Rule;
import com.github.selwynshen.nics.rules.api.Rules;
import com.github.selwynshen.nics.rules.api.RulesEngineListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class DefaultRulesEngineListener implements RulesEngineListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRulesEngineListener.class);

    private RulesEngineParameters parameters;

    DefaultRulesEngineListener(RulesEngineParameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public void beforeEvaluate(Rules rules, Facts facts) {
        if (!rules.isEmpty()) {
            logEngineParameters();
            log(rules);
            log(facts);
            LOGGER.info("Rules evaluation started");
        } else {
            LOGGER.warn("No rules registered! Nothing to apply");
        }
    }

    @Override
    public void afterExecute(Rules rules, Facts facts) {

    }

    private void logEngineParameters() {
        LOGGER.info(parameters.toString());
    }

    private void log(Rules rules) {
        LOGGER.info("Registered rules:");
        for (Rule rule : rules) {
            LOGGER.info("Rule { name = '{}', description = '{}', priority = '{}'}",
                    rule.getName(), rule.getDescription(), rule.getPriority());
        }
    }

    private void log(Facts facts) {
        LOGGER.info("Known facts:");
        for (Map.Entry<String, Object> fact : facts) {
            LOGGER.info("Fact { {} : {} }",
                    fact.getKey(), String.valueOf(fact.getValue()));
        }
    }
}